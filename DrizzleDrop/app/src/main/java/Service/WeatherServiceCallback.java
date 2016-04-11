package Service;

import data.Channel;

/**
 * Created by David on 4/11/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
