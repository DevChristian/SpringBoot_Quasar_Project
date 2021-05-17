package com.christian.mendez.quasarfireoperation.service;

import com.christian.mendez.quasarfireoperation.dto.SatelliteDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretRequestDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretResponseDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretSplitResponseDto;

import java.util.List;

public interface QuasarFireOperationService {

    TopSecretResponseDto obtainPositionAndMessage(TopSecretRequestDto topSecretRequestDto);

    TopSecretSplitResponseDto obtainPositionAndMessageSplit(SatelliteDto satelliteDto);

    TopSecretSplitResponseDto obtainPositionAndMessageSplit(String satellite_name,Double satellite_distance, List<String> satellite_message);
}
