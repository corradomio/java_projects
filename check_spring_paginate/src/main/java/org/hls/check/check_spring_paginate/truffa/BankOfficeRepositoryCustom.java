package org.hls.check.check_spring_paginate.truffa;

import java.util.List;

public interface BankOfficeRepository {

    List<BankOffice> findAll();
}
