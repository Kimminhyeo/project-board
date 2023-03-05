package com.min.projectboard.config;

import com.min.projectboard.domain.UserAccount;
import com.min.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp(){
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(
                UserAccount.of( "ualsTest",
                        "pw",
                        "ualsg@email.com",
                        "uals-test",
                        "test memo")
        ));
    }
}
