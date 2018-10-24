package com.flowerweather.android.status;

public class AddressStatus {
    //获取城市信息的地址
    public static String getCityAdd="https://search.heweather.com/find?key=2620809ea8c54772a181a2a88c503f31&location=";

    //天气实况
    public static String getWeatherNow="https://api.seniverse.com/v3/weather/now.json?key=umhhkhmvbnfytkef&language=zh-Hans&unit=c&location=";

    //生活指数
    public static String getSuggestion="https://api.seniverse.com/v3/life/suggestion.json?key=umhhkhmvbnfytkef&language=zh-Hans&location=";

    //逐日天气
    public static String getDaily="https://api.seniverse.com/v3/weather/daily.json?key=umhhkhmvbnfytkef&language=zh-Hans&unit=c&location=";
}
