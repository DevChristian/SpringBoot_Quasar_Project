package com.christian.mendez.quasarfireoperation.service;

import com.christian.mendez.quasarfireoperation.dto.JwtResponseDto;

import java.util.Map;

public interface JwtGenerateService {

    JwtResponseDto getJWTToken(Map<String, String> body);
}
