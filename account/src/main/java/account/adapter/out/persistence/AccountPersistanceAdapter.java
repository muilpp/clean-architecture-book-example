package account.adapter.out.persistence;

import account.application.port.out.LoadAccountPort;
import account.application.port.out.UpdateAccountStatePort;
import account.domain.Account;
import account.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountPersistanceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity account = accountRepository.findById(accountId.getValue()).orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.getValue(), baselineDate);
        Long withdrawalBalance = orZero(activityRepository.getWithdrawalBalanceUntil());
        Long depositBalance = orZero(activityRepository.getDepositBalanceUntil());

        return accountMapper.mapToDomainEntity(account, activities, withdrawalBalance, depositBalance);
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }
}
