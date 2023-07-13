package travelbeeee.spring_core_concept.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        bean1.addCount();
        bean2.addCount();

        Assertions.assertThat(bean1.getCount()).isEqualTo(1);
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
        Assertions.assertThat(bean1).isNotEqualTo(bean2);

        ac.close();
    }

    @Test
    void singletonTypeBeanWithPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);

        SingletonBean singletonBean = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        singletonBean.logic();
        singletonBean.logic();
        singletonBean.logic();

        Assertions.assertThat(singletonBean.getCount()).isEqualTo(3);

        /**
         * 싱글톤 빈 이므로 내부에 있는 PrototypeBean 이 한 번 주입되고 계속 동일하다.
         */
        singletonBean2.logic();
        Assertions.assertThat(singletonBean2.getCount()).isNotEqualTo(1);
    }

    @Test
    void singletonTypeBeanWithPrototype2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean2.class, PrototypeBean.class);

        SingletonBean2 singletonBean = ac.getBean(SingletonBean2.class);
        SingletonBean2 singletonBean2 = ac.getBean(SingletonBean2.class);

        Assertions.assertThat(singletonBean.logic()).isEqualTo(1);

        /**
         * 싱글톤 빈 이므로 내부에 있는 PrototypeBean 이 한 번 주입되고 계속 동일하다.
         */
        Assertions.assertThat(singletonBean2.logic()).isEqualTo(1);
    }

    @Test
    void singletonTypeBeanWithPrototype3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean3.class, PrototypeBean.class);

        SingletonBean3 singletonBean = ac.getBean(SingletonBean3.class);
        SingletonBean3 singletonBean2 = ac.getBean(SingletonBean3.class);

        singletonBean.setPrototypeBean();
        singletonBean.logic();
        singletonBean.logic();
        singletonBean.logic();

        Assertions.assertThat(singletonBean.getCount()).isEqualTo(3);

        /**
         * 싱글톤 빈 이므로 내부에 있는 PrototypeBean 이 한 번 주입되고 계속 동일하다.
         */
        singletonBean2.setPrototypeBean();
        singletonBean2.logic();
        Assertions.assertThat(singletonBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonTypeBeanWithPrototype4() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean4.class, PrototypeBean.class);

        SingletonBean4 singletonBean = ac.getBean(SingletonBean4.class);
        SingletonBean4 singletonBean2 = ac.getBean(SingletonBean4.class);

        Assertions.assertThat(singletonBean.logic()).isEqualTo(1);

        /**
         * 싱글톤 빈 이므로 내부에 있는 PrototypeBean 이 한 번 주입되고 계속 동일하다.
         */
        Assertions.assertThat(singletonBean2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class SingletonBean {
        private final PrototypeBean prototypeBean;

        public SingletonBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public void logic() {
            prototypeBean.addCount();
        }

        public int getCount() {
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

    @Scope("singleton")
    static class SingletonBean2 {
        /**
         * 스프링에서 제공하는 Provider 를 통해 PrototypeBean 을 주입 받을 수 있음.
         */
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

    @Scope("singleton")
    static class SingletonBean3 {
        private PrototypeBean prototypeBean;
        /**
         * 스프링에서 제공하는 Provider 를 통해 PrototypeBean 을 주입 받을 수 있음.
         * => 스프링 프레임워크에 의존
         */
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        public void setPrototypeBean() {
            this.prototypeBean = prototypeBeanObjectProvider.getObject();
        }

        public void logic() {
            prototypeBean.addCount();
        }

        public int getCount() {
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

    @Scope("singleton")
    static class SingletonBean4 {
        /**
         * 자바 제공하는 Provider 를 통해 PrototypeBean 을 주입 받을 수 있음.
         */
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;


        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy " + this);
        }

    }
}
