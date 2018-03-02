package com.reaier.engking.translate.impl.iciba.response;

import com.reaier.core.utils.JsonUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IcibaResultTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    final String JSON = "{\"word_name\":\"go\",\"is_CRI\":1,\"exchange\":{\"word_pl\":[\"goes\"],\"word_past\":[\"went\"],\"word_done\":[\"gone\"],\"word_ing\":[\"going\"],\"word_third\":[\"goes\"],\"word_er\":\"\",\"word_est\":\"\"},\"symbols\":[{\"ph_en\":\"g\\u0259\\u028a\",\"ph_am\":\"go\\u028a\",\"ph_other\":\"\",\"ph_en_mp3\":\"http:\\/\\/res.iciba.com\\/resource\\/amp3\\/0\\/0\\/34\\/d1\\/34d1f91fb2e514b8576fab1a75a89a6b.mp3\",\"ph_am_mp3\":\"http:\\/\\/res.iciba.com\\/resource\\/amp3\\/1\\/0\\/34\\/d1\\/34d1f91fb2e514b8576fab1a75a89a6b.mp3\",\"ph_tts_mp3\":\"http:\\/\\/res-tts.iciba.com\\/3\\/4\\/d\\/34d1f91fb2e514b8576fab1a75a89a6b.mp3\",\"parts\":[{\"part\":\"vi.\",\"means\":[\"\\u8d70\",\"\\u79bb\\u5f00\",\"\\u53bb\\u505a\",\"\\u8fdb\\u884c\"]},{\"part\":\"vt.\",\"means\":[\"\\u53d8\\u5f97\",\"\\u53d1\\u51fa\\u2026\\u58f0\\u97f3\",\"\\u6210\\u4e3a\",\"\\u5904\\u4e8e\\u2026\\u72b6\\u6001\"]},{\"part\":\"n.\",\"means\":[\"\\u8f6e\\u5230\\u7684\\u987a\\u5e8f\",\"\\u7cbe\\u529b\",\"\\u5e72\\u52b2\",\"\\u5c1d\\u8bd5\"]}]}],\"items\":[\"\"]}";

    @Test
    public void testResult() throws IOException {
        IcibaResult result = JsonUtils.getJsonWithJackson(JSON, IcibaResult.class);

        logger.info(JSON);
        logger.info(result.toString());
    }
}