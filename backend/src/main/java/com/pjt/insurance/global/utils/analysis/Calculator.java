package com.pjt.insurance.global.utils.analysis;



import com.sail.back.report.model.dto.request.analysis.CoordinateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;
@Service
@RequiredArgsConstructor
public class Calculator {
    public double slope(CoordinateDto p1, CoordinateDto p2) {
        if((p2.getX() - p1.getX()) == 0 )
            return  0;
        else
            return (double) ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
    }

    public double distance(CoordinateDto p1, CoordinateDto p2) {
        double dx = p1.getX()-p2.getX();
        double dy = p1.getY()-p2.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    public int closestY(ArrayList<CoordinateDto> list, int y) {

        double minDifference = Double.MAX_VALUE;
        int closestY = 64;
        // leftContourLeft 안에 있는 객체들 중에서 가장 가까운 X 값을 찾음
        for (int i = 0; i < list.size(); i++) {
            double difference = Math.abs(y - list.get(i).getY());
            if (difference < minDifference) {
                minDifference = difference;
                closestY = i;
            }
        }

        return closestY;
    }

    public int closestX(ArrayList<CoordinateDto> list, int x) {

        double minDifference = Double.MAX_VALUE;
        int closestX = 64;
        // leftContourLeft 안에 있는 객체들 중에서 가장 가까운 X 값을 찾음
        for (int i = 0; i < list.size(); i++) {
            double difference = Math.abs(x - list.get(i).getX());
            if (difference < minDifference) {
                minDifference = difference;
                closestX = i;
            }
        }

        return closestX;
    }

    public int findMaximalY(ArrayList<CoordinateDto> list) {

        int maxNow = list.get(0).getY();
        int maxY = 64;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getY() >= maxNow) {
                maxNow = list.get(i).getY();
                maxY = i;
            }
        }

        return maxY;
    }

    public int findMinimalY(ArrayList<CoordinateDto> list) {

        int minNow = list.get(0).getY();
        int minY = 64;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getY() <= minNow) {
                minNow = list.get(i).getY();
                minY = i;
            }
        }

        return minY;
    }

    public int findMaximalX(ArrayList<CoordinateDto> list) {
        int maxNow = list.get(0).getX();
        int maxX = 64;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() >= maxNow) {
                maxNow = list.get(i).getX();
                maxX = i;
            }
        }

        return maxX;
    }

    public int findMinimalX(ArrayList<CoordinateDto> list) {
        int minNow = list.get(0).getX();
        int minX = 64;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() <= minNow) {
                minNow = list.get(i).getX();
                minX = i;
            }
        }

        return minX;
    }

    public ArrayList<CoordinateDto> seperateUpper(ArrayList<CoordinateDto> list){
        ArrayList<CoordinateDto> upperList = new ArrayList<>();
        for(int i = 0; i <32; i++){
            upperList.add(list.get(i));
        }
        return upperList;
    }

    public ArrayList<CoordinateDto> seperateLower(ArrayList<CoordinateDto> list){
        ArrayList<CoordinateDto> lowerList = new ArrayList<>();
        for(int i = 32; i < 64; i++){
            lowerList.add(list.get(i));
        }
        return lowerList;
    }

    public int closestPoint(ArrayList<CoordinateDto> list, CoordinateDto p) {

        double minDistance = Double.MAX_VALUE;
        int closestIndex = 64;

        for (int i = 0; i < 64; i++) {
            CoordinateDto current = list.get(i);
            double distance = distance(current, p);

            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }

        return closestIndex;
    }
    //이차방정식 생서하는 메서드
    public DoubleUnaryOperator createQuadraticEquation(CoordinateDto p1, CoordinateDto p2, CoordinateDto p3) {
        // 직선 방정식의 계수 계산
        double a = (double) ((p2.getY() - p1.getY()) * (p3.getX() - p1.getX()) - (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()))
                / ((p2.getX() - p1.getX()) * (p3.getX() - p1.getX()) * (p2.getX() - p3.getX()));
        double b = (p2.getY() - p1.getY() - a * (p2.getX() * p2.getX() - p1.getX() * p1.getX()))
                / (p2.getX() - p1.getX());
        double c = p1.getY() - a * p1.getX() * p1.getX() - b * p1.getX();

        // 이차방정식 생성 및 반환
        return x -> a * x * x + b * x + c;
    }


    // 구간 [leftEnd, rightEnd]에서의 길이를 계산하는 메서드
    public double calculateLength(DoubleUnaryOperator function, double leftEnd, double rightEnd) {
        double length = 0.0;

        // 구간을 delta 간격으로 나누어 각 점에서의 접선의 길이를 계산하여 적분
        for (double t = leftEnd; t < rightEnd; t += 0.0000001) {
            double derivative = calculateDerivative(function, t);
            double integrand = Math.sqrt(1 + Math.pow(derivative, 2));
            length += integrand * 0.0000001;
        }

        return length;
    }

    // 주어진 함수 function에서 t에서의 미분값을 계산하는 메서드
    private double calculateDerivative(DoubleUnaryOperator function, double t) {
        // 수치적 미분을 사용하여 근사적으로 미분값 계산
        double delta = 0.0000001;
        double valueAtT = function.applyAsDouble(t);
        double valueAtTPlusDelta = function.applyAsDouble(t + delta);
        return (valueAtTPlusDelta - valueAtT) / delta;
    }

    private double calculateX(double t, double elipsWidth) {
        return elipsWidth * Math.cos(t);
    }

    // 타원의 방정식에 따른 y 값 계산
    private double calculateY(double t, double elipsHeight) {
        return elipsHeight * Math.sin(t);
    }

    // leftEnd와 rightEnd 사이의 길이를 계산하는 메서드
    public double calculateLength(double elipsWidth, double elipsHeight, double leftEnd, double rightEnd, int segments) {
        // 각 구간의 길이를 저장할 변수
        double length = 0.0;

        // 각 구간에서의 길이를 계산하여 합산
        double deltaT = (rightEnd - leftEnd) / segments;
        for (int i = 0; i < segments; i++) {
            double t1 = leftEnd + i * deltaT;
            double t2 = leftEnd + (i + 1) * deltaT;

            // 각 구간에서의 점들을 계산하고 길이를 계산하여 합산
            double x1 = calculateX(t1, elipsWidth);
            double y1 = calculateY(t1, elipsHeight);
            double x2 = calculateX(t2, elipsWidth);
            double y2 = calculateY(t2, elipsHeight);

            // 두 점 사이의 거리 계산
            double segmentLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            // 각 구간의 길이를 합산
            length += segmentLength;
        }

        return length;
    }
}
