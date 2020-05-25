package com.trump.library_common.permission;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public abstract class PermissionListener implements Consumer<Boolean> {

    @Override
    public void accept(@NonNull Boolean granted) throws Exception {
        result(granted);
    }

    public abstract void result(boolean granted);
}
