package travelbeeee.spring_core_concept.singleton;

/**
 * 싱글톤 패턴 구현
 * - static 필드에 객체 인스턴스를 미리 하나 생성해서 올려둔다.
 * - private 생성자를 통해 외부에서 객체 인스턴스 생성을 막는다.
 */
public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }
}
