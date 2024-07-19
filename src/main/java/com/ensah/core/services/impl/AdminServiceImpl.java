package com.ensah.core.services.impl;
import com.ensah.core.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensah.core.bo.Admin;
import com.ensah.core.dao.*;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
