
package com.c.sahibindenweatherapp.api.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherItems implements Parcelable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("snow")
    @Expose
    private Double snow;


    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
        this.snow = snow;
    }

    protected WeatherItems(Parcel in) {
        if (in.readByte() == 0) {
            dt = null;
        } else {
            dt = in.readInt();
        }
        temp = in.readParcelable(Temp.class.getClassLoader());
        if (in.readByte() == 0) {
            pressure = null;
        } else {
            pressure = in.readDouble();
        }
        if (in.readByte() == 0) {
            humidity = null;
        } else {
            humidity = in.readInt();
        }
        weather = in.createTypedArrayList(Weather.CREATOR);
        if (in.readByte() == 0) {
            speed = null;
        } else {
            speed = in.readDouble();
        }
        if (in.readByte() == 0) {
            deg = null;
        } else {
            deg = in.readInt();
        }
        if (in.readByte() == 0) {
            clouds = null;
        } else {
            clouds = in.readInt();
        }
        if (in.readByte() == 0) {
            snow = null;
        } else {
            snow = in.readDouble();
        }
    }

    public static final Creator<WeatherItems> CREATOR = new Creator<WeatherItems>() {
        @Override
        public WeatherItems createFromParcel(Parcel in) {
            return new WeatherItems(in);
        }

        @Override
        public WeatherItems[] newArray(int size) {
            return new WeatherItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (dt == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(dt);
        }
        parcel.writeParcelable(temp, i);
        if (pressure == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(pressure);
        }
        if (humidity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(humidity);
        }
        parcel.writeTypedList(weather);
        if (speed == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(speed);
        }
        if (deg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(deg);
        }
        if (clouds == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(clouds);
        }
        if (snow == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(snow);
        }
    }
}
