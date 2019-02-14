package com.alvastudio.simplereader;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.model.ReadPosition;
import com.folioreader.util.ReadPositionListener;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import static com.alvastudio.simplereader.Constants.YANDEX_API_KEY;

public class MainActivity extends AppCompatActivity implements FolioReader.OnClosedListener, ReadPositionListener {

    FolioReader folioReader;
    ReadPosition sReadPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initYandex();

        Config config = new Config()
                .setAllowedDirection(Config.AllowedDirection.ONLY_VERTICAL)
                .setDirection(Config.Direction.VERTICAL)
                .setFont(Constants.FONT_ANDADA)
                .setFontSize(2)
                .setNightMode(false)
                .setThemeColorRes(R.color.red)
                .setShowTts(true);
        folioReader = FolioReader.get();
        folioReader = FolioReader.get()
                .setReadPositionListener(this)
                .setOnClosedListener(this);

        folioReader.setConfig(config, true);

        folioReader.openBook(R.raw.book);
    }

    private void initYandex() {
        // Создание расширенной конфигурации библиотеки.
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(YANDEX_API_KEY).build();
        // Инициализация AppMetrica SDK.
        YandexMetrica.activate(getApplicationContext(), config);
        // Отслеживание активности пользователей.
        YandexMetrica.enableActivityAutoTracking(getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(keyCode, event);
            return true;
        }
        return false;
    }

    @Override
    public void onFolioReaderClosed() {
        folioReader.openBook(R.raw.book);
    }

    @Override
    public void saveReadPosition(ReadPosition readPosition) {

    }
}
