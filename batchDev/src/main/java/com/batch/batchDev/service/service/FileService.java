package com.batch.batchDev.service.service;

import com.batch.batchDev.service.vo.NllpVO;

import java.util.List;

/**
 * packageName    : com.batch.batchDev.service.service
 * fileName       : FileService
 * author         : hyeokchan
 * date           : 2023/02/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/16        hyeokchan       최초 생성
 */
public interface FileService {
    void writeFile(List<NllpVO> items) throws Exception;
}
