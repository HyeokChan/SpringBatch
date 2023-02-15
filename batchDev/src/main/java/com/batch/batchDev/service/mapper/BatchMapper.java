package com.batch.batchDev.service.mapper;

import com.batch.batchDev.service.vo.NllpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName    : com.batch.batchDev.service.mapper
 * fileName       : BatchMapper
 * author         : hyeokchan
 * date           : 2023/02/15
 * description    : 배치처리 mapper interface
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/15        hyeokchan       최초 생성
 */
@Mapper
public interface BatchMapper {
    List<NllpVO> findNllpList(NllpVO inVO) throws Exception;
    int instNllpInfo(NllpVO inVO) throws Exception;
    int updtNllpInfo(NllpVO inVO) throws Exception;
}
