package com.danit.erp.auth.reset_password;

import org.springframework.data.jpa.repository.JpaRepository;

interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

  PasswordResetToken findByToken(String theToken);
}
