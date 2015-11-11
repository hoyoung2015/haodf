package net.hoyoung.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by hoyoung on 2015/11/11.
 */
public class DuihuaPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.get("wenda"));
        System.out.println(resultItems.get("duihuas"));
    }
}
