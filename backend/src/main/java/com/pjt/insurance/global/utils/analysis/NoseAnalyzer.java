package com.pjt.insurance.global.utils.analysis;



import com.sail.back.report.model.dto.request.analysis.CoordinateDto;
import com.sail.back.report.model.entity.enums.AlarSize;
import com.sail.back.report.model.entity.enums.NoseSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class NoseAnalyzer {
    private final Calculator calculator;

    public NoseSize noseSizeAnalyzer(ArrayList<CoordinateDto> leftNose, ArrayList<CoordinateDto> rightNose, CoordinateDto leftPoint, CoordinateDto rightPoint){
        //코의 가장 왼쪽, 오른쪽 좌표
        int leftNosePoint = calculator.findMinimalX(leftNose);
        int rightNosePoint = calculator.findMaximalX(rightNose);
        //코 폭
        double lengthValue = rightNose.get(rightNosePoint).getX() -leftNose.get(leftNosePoint).getX();
        //미간 길이
        double standardValue = rightPoint.getX()-leftPoint.getX();
        //비율 구하기
        double compareValue = lengthValue/standardValue;

        if(compareValue > 1.1){
            return NoseSize.BIG_NOSE;
        }else if(compareValue < 0.9){
            return NoseSize.SMALL_NOSE;
        }else{
            return NoseSize.MIDDLE_NOSE;
        }
    }

    public AlarSize alarSizeAnalyzer(ArrayList<CoordinateDto> leftNose, ArrayList<CoordinateDto> rightNose){
        //콧볼 좌우끝
        int leftNosePoint = calculator.findMinimalX(leftNose);
        int rightNosePoint = calculator.findMaximalX(rightNose);

        //콧대(코윤곽의 작은 값)을 구하기위한 분리
        ArrayList<CoordinateDto> leftUpperNose = calculator.seperateUpper(leftNose);
        ArrayList<CoordinateDto> rightUpperNose = calculator.seperateUpper(rightNose);

        //콧대 좌우
        int leftMiddleNosePoint = calculator.findMaximalX(leftUpperNose);
        int rightMiddleNosePoint = calculator.findMinimalX(rightUpperNose);

        double noseWidth = rightNose.get(rightNosePoint).getX() - leftNose.get(leftNosePoint).getX();
        double noseMiddle = rightUpperNose.get(rightMiddleNosePoint).getX() - leftUpperNose.get(leftMiddleNosePoint).getX();

        double compareValue = noseMiddle/noseWidth;

        if(compareValue > 0.85){
            return AlarSize.SMALL_ALAR;
        }else if(compareValue < 0.75){
            return AlarSize.BIG_ALAR;
        }else{
            return AlarSize.PERFECT_ALAR;
        }

    }
}
