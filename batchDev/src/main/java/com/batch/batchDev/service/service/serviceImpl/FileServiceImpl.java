package com.batch.batchDev.service.service.serviceImpl;

import com.batch.batchDev.service.service.FileService;
import com.batch.batchDev.service.vo.NllpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.batch.batchDev.service.service.serviceImpl
 * fileName       : FileServiceImpl
 * author         : hyeokchan
 * date           : 2023/02/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/16        hyeokchan       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public void writeFile(List<NllpVO> items) throws Exception {

    }
}
