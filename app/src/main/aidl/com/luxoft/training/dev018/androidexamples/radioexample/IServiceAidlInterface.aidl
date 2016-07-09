// IServiceAidlInterface.aidl
package com.luxoft.training.dev018.androidexamples.radioexample;

import com.luxoft.training.dev018.androidexamples.radioexample.IStreamingServiceCallbacks;

interface IServiceAidlInterface {

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    void registerCallbacks(IStreamingServiceCallbacks callbacks);

    void unregisterCallbacks();
}
