package com.batch.batchDev.service.service.serviceImpl;

import com.batch.batchDev.service.mapper.BatchMapper;
import com.batch.batchDev.service.service.FileService;
import com.batch.batchDev.service.service.UpdtService;
import com.batch.batchDev.service.vo.NllpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UpdtServiceImpl implements UpdtService {
//    @Autowired
//    private BatchMapper batchMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void instNllpList(List<NllpVO> items) throws Exception {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            BatchMapper batchMapper = session.getMapper(BatchMapper.class);
            for(NllpVO item : items){
                batchMapper.instNllpInfo(item);
            }
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updtNllpList(List<NllpVO> items) throws Exception {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            BatchMapper batchMapper = session.getMapper(BatchMapper.class);
            for(NllpVO item : items){
                batchMapper.updtNllpInfo(item);
            }
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
