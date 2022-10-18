package com.involuntary.revpos.listeners;


import com.involuntary.revpos.models.MenuItem;

import java.io.IOException;

public interface MyListener {
    public void onClickListener(MenuItem item) throws IOException;
}
