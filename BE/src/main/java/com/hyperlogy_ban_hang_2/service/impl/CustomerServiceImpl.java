package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.CustomerDto;
import com.hyperlogy_ban_hang_2.dto.RegistrationDto;
import com.hyperlogy_ban_hang_2.dto.request.CustomerRegisterReq;
import com.hyperlogy_ban_hang_2.entity.Bill;
import com.hyperlogy_ban_hang_2.entity.BillDetail;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import com.hyperlogy_ban_hang_2.entity.Customer;
import com.hyperlogy_ban_hang_2.mapper.BillDetailMapper;
import com.hyperlogy_ban_hang_2.mapper.BillMapper;
import com.hyperlogy_ban_hang_2.mapper.CustomerMapper;
import com.hyperlogy_ban_hang_2.repository.BillDetailRepository;
import com.hyperlogy_ban_hang_2.repository.BillRepository;
import com.hyperlogy_ban_hang_2.repository.CustomerRepository;
import com.hyperlogy_ban_hang_2.service.CustomerService;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.auth.AuthenticationService;
import com.hyperlogy_ban_hang_2.util.GenMaHD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements GeneralService<CustomerDto>, CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final GenMaHD genMaHD;
    private final BillRepository billRepository;

    private final BillDetailRepository billDetailRepository;
    private final BillDetailMapper billDetailMapper;
    private final AuthenticationService authenticationService;

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(customer -> customerMapper.mapCusToCusDto(customer)).toList();
    }

    @Override
    public String saveOrUpdate(CustomerDto customerDto) {
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
//        customer.setRole("CUSTOMER");
        customerRepository.save(customer);
        return "Thanh cong";
    }

    @Override
    public CustomerDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            return customerMapper.mapCusToCusDto(customer);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            customerRepository.delete(customer);
        }
        return "Da xoa";
    }

    @Override
    public String datHang(List<CartDetail> listCartDetail) {
        //bam dat hang => tao hoa don moi voi cac thong tin:
        //nguoi nhan fix cung la customer
        //customer login
        Customer customer = customerRepository.findById(1L).orElse(null);
        Bill bill = Bill.builder()
                .status(0)
                .customer(customer)
                .receicverName(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .code(genMaHD.autoGen())
                .createdTime(new Timestamp(new Date().getTime()))
                .receiverAddress(customer.getAddress())
                .build();
        billRepository.save(bill);
        //chuyen nhung sp trong list cart detail sang bill detail
        for (CartDetail cartDetail : listCartDetail
        ) {
            BillDetail billDetail = billDetailMapper.mapCartDetailToBillDetail(cartDetail);
            billDetail.setBill(bill);
            billDetailRepository.save(billDetail);
        }
        return "Thanh cong";
    }

    @Override
    public CustomerDto getByEmalAndPass(String email, String pass) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, pass);
        if (customer != null) {
            return customerMapper.mapCusToCusDto(customer);
        } else {
            return null;
        }
    }

    @Override
    public String dangKiTaiKhoanUser(CustomerRegisterReq customerRegisterReq) {
        RegistrationDto registrationDto = RegistrationDto.builder()
                .roleId(customerRegisterReq.getRoleId())
                .password(customerRegisterReq.getPassword())
                .email(customerRegisterReq.getEmail())
                .build();
        try {
            String email = authenticationService.register(registrationDto);
            Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow(() -> new RuntimeException("khong tim thay"));
            customer.setAddress(customerRegisterReq.getAddress());
            customer.setPhoneNumber(customerRegisterReq.getPhoneNumber());
            customer.setCode(customerRegisterReq.getCode());
            customer.setName(customerRegisterReq.getName());
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Done";
    }
}
