package com.thesilentnights.openchestanywhere.utils.messageSender.messageImp;

import com.thesilentnights.openchestanywhere.OpenChestAnywhere;
import com.thesilentnights.openchestanywhere.utils.messageSender.Message;

public class TestMessage extends Message {
    public TestMessage(String body) {
        this.body = body;
    }

    @Override
    protected void send() {
        OpenChestAnywhere.getInstance().getLogger().info(body);
    }
}
