package com.example.demo.BorrowPackage;

import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) {
        this.repo = repo;
    }


    public Borrow save(Borrow borrow){
        return repo.save(borrow);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public Borrow findById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow not found!"));
    }

}
