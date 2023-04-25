package com.reaier.engking.sequence.ocr.tencent;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.ocr.AbstractOCR;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.ocr.describe.Point;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.utils.Copier;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

// https://cloud.tencent.com/document/product/866/34938
// 默认接口请求频率限制：10次/秒
@Service
public class TencentOCR extends AbstractOCR {
    private final static String SECRET_ID   = "AKIDSmI7KatAl12eQFQyI63EbAWyX9xmJ6qQ";
    private final static String SECRET_KEY  = "2W6MDGuCxoLJtvapr0qwQ035zs66RsB2";

    @Override
    public void ocr(Source source) throws OCRException {
        EnglishOCRResponse response;
        try {
            response = ocrEnglish(source.getContent());
        } catch (TencentCloudSDKException e) {
            throw new OCRException(e);
        }

        // 用于提取整个文章
        StringBuffer sb = new StringBuffer ();

        // 用于提取整个单词的坐标系
        List<String> words      = new LinkedList<>();
        List<Coordinate> coords = new LinkedList<>();

        TextDetectionEn[] ens = response.getTextDetections();
        for (TextDetectionEn item : ens) {
            sb.append(item.getDetectedText()).append(' ');

            Words[] ttWords = item.getWords();
            WordCoordPoint[] ttPoints = item.getWordCoordPoint();

            // 根据每行对应的句子，将每个单词单独拿出来
            for (int i = 0; i < ttWords.length; i++) {
                // 此行中有多少个单词
                Words ttWord = ttWords[i];

                words.add(ttWord.getCharacter());

                // 说明指定了单词对应的坐标
                List<Long> points = new ArrayList<>(8);
                WordCoordPoint ttWordCoord = i < ttPoints.length ? ttPoints[i] : null;
                if (null != ttWordCoord ) {
                    Coord[] ttCoords = ttWordCoord.getWordCoordinate();
                    if (null != ttCoords) {
                        for (int j = 0; j < ttCoords.length; j++) {
                            points.add(ttCoords[j].getX());
                            points.add(ttCoords[j].getY());
                        }
                    }
                }

                coords.add(null == points ? null : Coordinate.builder().word(ttWord.getCharacter()).points(points).build());
            }
        }

        if (sb.length() > 0) {
            source.setContent(sb.substring(0, sb.length() -1));
            source.setCoordinate(coords);
        }
    }

    private EnglishOCRResponse ocrEnglish(@NotNull String url) throws TencentCloudSDKException {
        // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
        Credential cred = new Credential(SECRET_ID, SECRET_KEY);

        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("ocr.tencentcloudapi.com");

        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        clientProfile.setDebug(true);

        // 实例化要请求产品的client对象,clientProfile是可选的
        OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

        // 实例化一个请求对象,每个接口都会对应一个request对象
        EnglishOCRRequest req = new EnglishOCRRequest();
        req.setImageUrl(url);
        req.setEnableCoordPoint(true);
        req.setPreprocess(true);

        try {

            // 返回的resp是一个EnglishOCRResponse的实例，与请求对象对应
            return client.EnglishOCR(req);
        } catch (TencentCloudSDKException e) {
            throw e;
        }
    }
}
