//package summary.news_line.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.io.IOException;
//import java.time.Instant;
//
///**
// * LINE Webhook (line.bot.handler.path=/callback) が本当に叩かれているかを切り分けるためのログ。
// * 認証や署名の検証より前に HTTP レベルで確認する。
// */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class LineWebhookLoggingFilter extends OncePerRequestFilter {
//    public LineWebhookLoggingFilter() {
//        System.out.println("[LineWebhookLoggingFilter] instantiated");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String uri = request.getRequestURI();
//        boolean hasLineSignature = request.getHeader("x-line-signature") != null;
//
//        // デバッグ目的: LINE の webhook が到達しているかを確実に見るため、
//        // POST は必ずログに出す（アクセスが多い環境では外してください）
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            String line = "[LineWebhookLoggingFilter] POST at=" + Instant.now()
//                    + " uri=" + uri
//                    + " contentLength=" + request.getContentLength()
//                    + " hasXLineSignature=" + hasLineSignature)
//            System.out.println(line);
//
//            // IntelliJ のコンソールが見えない場合でも追えるようにファイルへ追記
//            try {
//                Path logPath = Path.of("/Users/eshimayuusuke/workspace/news-line/line-webhook-debug.log");
//                Files.createDirectories(logPath.getParent());
//                Files.writeString(logPath,
//                        line + System.lineSeparator(),
//                        StandardCharsets.UTF_8,
//                        java.nio.file.StandardOpenOption.CREATE,
//                        java.nio.file.StandardOpenOption.APPEND);
//            } catch (Exception ignore) {
//                // ログファイル出力が失敗した場合は理由を出す（追跡用）
//                ignore.printStackTrace();
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
//
