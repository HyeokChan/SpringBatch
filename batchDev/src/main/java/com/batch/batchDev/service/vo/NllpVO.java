package com.batch.batchDev.service.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * packageName    : com.batch.batchDev.service.vo
 * fileName       : NllpVO
 * author         : hyeokchan
 * date           : 2023/02/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/15        hyeokchan       최초 생성
 */
@Getter
@Setter
@ToString
@Alias("nllpVO")
public class NllpVO {
    private String nllpAcbKey;
    private String nllpAcbNo;
    private String lgoCd;
    private String nllpNm;
    private String landAr;
    private String bldgAr;
    private String oalp;
    private String oalpYr;
    private String zip;
    private String stdgCd;
    private String lotnoBacAddr;
    private String lotnoDaddr;
    private String mtnSeCd;
    private String mno;
    private String sno;
    private String spclDg;
    private String spclHo;
    private String ldcgCd;
    private String rmCn;
    private String totCnt;
    private String lotnoAlAddr;
    private String lgoNm;
    private String mtnSeNm;
}
