package org.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: DashboardApp
 * @Auther: jchan
 * @Date: 2019/7/1 上午9:54
 * @Version 1.0 2019/7/1 上午9:54 by 韩进城(hanjin7278@126.com)
 * @Description:
 **/
/*@SpringBootApplication
public class DashboardApp  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DashboardApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DashboardApp.class, args);
    }

}*/

@SpringBootApplication
public class DashboardApp {
    public static void main(String[] args) {
        SpringApplication.run(DashboardApp.class, args);
    }
}