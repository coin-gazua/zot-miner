# zot-miner

### profile 적용
 * IDE : VM OPTIONS
   * -Dspring.profiles.active=local
     * (환경에 따라 local, dev, real 로 변경)
 * 실행 시
   * java -jar -Dspring.profiles.active=real zot-miner-0.0.1-SNAPSHOT.jar

### jssecacerts 적용
 1. curl -O https://gist.githubusercontent.com/lesstif/cd26f57b7cfd2cd55241b20e05b5cd93/raw/InstallCert.java
 2. javac InstallCert.java
 3. InstallCert 구동
 * java -cp ./ InstallCert api.bithumb.com
 4. 1로 저장 -> jssecacerts 파일 생성 됨
 5. jssecacerts 파일 -> ${JAVA_HOME}/jre/lib/security/ 로 복사
 6. 애플리케이션 재실행
