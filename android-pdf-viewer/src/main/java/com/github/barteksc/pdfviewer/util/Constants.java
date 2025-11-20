/**
 * Copyright 2016 Bartosz Schiller
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.barteksc.pdfviewer.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class Constants {

    public static boolean DEBUG_MODE = false;

    /** Between 0 and 1, the thumbnails quality (default 0.3). Increasing this value may cause performance decrease */
    public static float THUMBNAIL_RATIO = 0.4f;

    /**
     * The size of the rendered parts (default 256)
     * Tinier : a little bit slower to have the whole page rendered but more reactive.
     * Bigger : user will have to wait longer to have the first visual results
     */
    // Temel değer
    private static final float BASE_PART_SIZE = 256;

    // Dinamik olarak hesaplanacak
    public static float PART_SIZE = BASE_PART_SIZE;

    /**
     * Cihaz özelliklerine göre PART_SIZE'ı ayarla
     * Activity veya Application onCreate'de çağırılmalı
     */
    public static void initPartSize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;
        int screenWidth = metrics.widthPixels;

        // Düşük yoğunluklu cihazlar için küçük PART_SIZE
        if (density <= 1.5f || screenWidth < 720) {
            PART_SIZE = 256;
        }
        // Orta yoğunluklu cihazlar
        else if (density <= 2.0f || screenWidth < 1080) {
            PART_SIZE = 384;
        }
        // Yüksek yoğunluklu cihazlar
        else if (density <= 3.0f || screenWidth < 1440) {
            PART_SIZE = 512;
        }
        // Çok yüksek yoğunluklu cihazlar
        else {
            PART_SIZE = 700;
        }

        if (DEBUG_MODE) {
            Log.d("Constants", "PART_SIZE set to: " + PART_SIZE +
                    " (density: " + density + ", width: " + screenWidth + ")");
        }
    }

    /** Part of document above and below screen that should be preloaded, in dp */
    public static int PRELOAD_OFFSET = 10;

    public static class Cache {

        /** The size of the cache (number of bitmaps kept) */
        public static int CACHE_SIZE = 150;

        public static int THUMBNAILS_CACHE_SIZE = 6;
    }

    public static class Pinch {

        public static float MAXIMUM_ZOOM = 6;

        public static float MINIMUM_ZOOM = 1;

    }

}
