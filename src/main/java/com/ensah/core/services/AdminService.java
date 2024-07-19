package com.ensah.core.services;
import com.ensah.core.bo.*;

public interface AdminService {
    Admin getAdminById(Long id);
    void saveAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void deleteAdmin(Long id);
}
