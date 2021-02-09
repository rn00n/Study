package me.rn00n.server.infra.init;

import me.rn00n.server.models.system.account.domain.Account;
import me.rn00n.server.models.system.account.dto.AccountSignUp;
import me.rn00n.server.models.system.account.repository.AccountRepository;
import me.rn00n.server.models.system.account.service.AccountService;
import me.rn00n.server.models.system.auth.domain.Auth;
import me.rn00n.server.models.system.auth.dto.AuthDto;
import me.rn00n.server.models.system.auth.repository.AuthRepository;
import me.rn00n.server.models.system.auth.service.AuthService;
import me.rn00n.server.models.system.resource.domain.Resource;
import me.rn00n.server.models.system.resource.repository.ResourceRepository;
import me.rn00n.server.models.system.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AppStartRunner implements ApplicationRunner {

    @Value("${init_data}")
    private boolean initData;

    @Autowired
    AccountService accountService;
    @Autowired AccountRepository accountRepository;

    @Autowired
    AuthService authService;
    @Autowired
    AuthRepository authRepository;

    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceRepository resourceRepository;

    private Account admin;

    private Set<Resource> resourcesForAdmin = new HashSet<>();

    private Set<Resource> resourcesForUser = new HashSet<>();

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (initData) {
            System.out.println("init data start...");

            Optional<Account> admin = accountRepository.findByUsername("admin");
            if (admin.isPresent()) {
                this.admin = admin.get();
            } else {
                AccountSignUp accountSignUp = AccountSignUp.builder()
                        .username("admin")
                        .password("1234")
                        .name("관리자")
                        .phoneNo("010-0000-0000")
                        .email("admin@mail.com")
                        .build();
                this.admin = accountService.saveAccount(accountSignUp);
            }

            initSystemResource();

            Optional<Auth> roleAdmin = authRepository.findById("ROLE_ADMIN");
            Auth authAdmin = null;
            if (roleAdmin.isEmpty()) {
                AuthDto authDto = AuthDto.builder()
                        .id("ROLE_ADMIN")
                        .name("관리자")
                        .description("권리자 권한")
                        .build();
                authAdmin = authService.saveAuth(authDto);
            } else {
                authAdmin = roleAdmin.get();
            }
            authAdmin.setResources(resourcesForAdmin);
            Set<Auth> authsForAdmin = new HashSet<>();
            authsForAdmin.add(authAdmin);
            this.admin.setAuths(authsForAdmin);

            Optional<Auth> roleUser = authRepository.findById("ROLE_USER");
            Auth authUser = null;
            if (roleUser.isEmpty()) {
                AuthDto authDto = AuthDto.builder()
                        .id("ROLE_USER")
                        .name("회원")
                        .description("회원 권한")
                        .build();
                authUser = authService.saveAuth(authDto);
            } else {
                authUser = roleUser.get();
            }
            authUser.setResources(resourcesForUser);
            Set<Auth> authsForUser = new HashSet<>();
            authsForUser.add(authUser);

            System.out.println("init data end.");
        }
    }

    private void initSystemResource() {
        Resource resourceHello = resourceRepository.findByName("Hello");
        if (resourceHello == null) {
            resourceHello = new Resource();
            resourceHello.setName("Hello");
            resourceHello.setUrl("/api/hello");
            resourceHello.setMethod("GET");
            resourceHello.setDescription("Hello");
            resourceRepository.save(resourceHello);
        }
        resourcesForAdmin.add(resourceHello);
        resourcesForUser.add(resourceHello);
    }
}
