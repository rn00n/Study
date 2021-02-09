package me.rn00n.server.models.system.account.service;

import me.rn00n.server.models.system.account.domain.Account;
import me.rn00n.server.models.system.account.dto.AccountSignUp;

public interface AccountService {
    public Account saveAccount(AccountSignUp accountSignUp);
    public Account getUserDetails(String username);
    public Account findOneByUsername(String username);
}
