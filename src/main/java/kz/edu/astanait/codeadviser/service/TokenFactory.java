package kz.edu.astanait.codeadviser.service;

import kz.edu.astanait.codeadviser.domain.GeneratedToken;

public interface TokenFactory {

    GeneratedToken create(String email);
}
