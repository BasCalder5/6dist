package Server;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepo extends JpaRepository<Account, Long> {
}
