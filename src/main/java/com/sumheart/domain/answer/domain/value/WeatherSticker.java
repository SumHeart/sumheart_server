package com.sumheart.domain.answer.domain.value;

import lombok.Getter;

@Getter
public enum WeatherSticker {

  SUNNY(1),              // 웃고 있는 해
  THUNDERSTORM(2),       // 번개가 있는 구름
  CLOUDY(3),             // 단순한 구름
  PARTLY_CLOUDY(4),      // 해와 구름이 함께 있는 모습
  RAINY(5),              // 비를 내리는 구름
  SUN_WITH_SUNGLASSES(6), // 선글라스를 쓴 해
  MOON(7),               // 웃고 있는 달
  WINDY(8),              // 바람을 내뿜는 구름
  RAINBOW(9);            // 무지개와 구름

  private final long code;

  WeatherSticker(long code) {
    this.code = code;
  }

  public static WeatherSticker fromCode(long code) {
    for (WeatherSticker sticker : WeatherSticker.values()) {
      if (sticker.getCode() == code) {
        return sticker;
      }
    }
    throw new IllegalArgumentException("Invalid code: " + code);
  }
}


