package default_package.枚举单实例测试;


class Main {
    public static void main(String[] args) {
        System.out.println(System.identityHashCode(Node.LEFT));
        Node.LEFT.value = "11111";
        System.out.println(System.identityHashCode(Node.LEFT));
    }
}
