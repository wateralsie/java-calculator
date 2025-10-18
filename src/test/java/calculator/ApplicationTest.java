package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 빈칸_테스트() {
        assertSimpleTest(() -> {
            // 사용자 엔터 입력 고려
            run("\\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 공백_테스트() {
        assertSimpleTest(() -> {
            List<String> blanks = List.of(" ", "   ", "\t", " \t\t ");
            blanks.forEach(blank -> {
                run(blank);
                assertThat(output())
                        .as("입력 : %s", blank.replace("\t", "\\t"))
                        .contains("결과 : 0");
            });
        });
    }

    @Test
    void 숫자만_존재() {
        assertSimpleTest(() -> {
            run("123");
            assertThat(output()).contains("결과 : 123");
        });
    }

    @Test
    void 유효한_구분자만_존재() {
        assertSimpleTest(() -> {
            run("//;\\n;,:");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 공백_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("// \\n23 45 2");
            assertThat(output()).contains("결과 : 70");
        });
    }

    @Test
    void 숫자_커스텀_구분자_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//3\n132333"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 하이픈_커스텀_구분자_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//-\\n-10--2--3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 길이_2_이상_커스텀_구분자_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//;;\\n1;;2;;3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 유효하지_않은_구분자_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1@2#3@"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_지정_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//*5*23*9"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_계산대상_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1:#:3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
