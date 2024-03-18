//package ru.leti.wise.task.graph.configuration;
//
//import brave.Tracing;
//import brave.grpc.GrpcTracing;
//import io.grpc.ClientInterceptor;
//import io.grpc.ServerInterceptor;
//import io.micrometer.observation.ObservationRegistry;
//import io.micrometer.observation.aop.ObservedAspect;
//import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TracingConfiguration {
//    @Bean
//    ObservedAspect observedAspect(ObservationRegistry registry) {
//        return new ObservedAspect(registry);
//    }
//
//    @Bean
//    GrpcTracing grpcTracing(Tracing tracing) {
//        return GrpcTracing.create(tracing);
//    }
//
//    @Bean
//    @GRpcGlobalInterceptor
//    ServerInterceptor grpcTracingServerInterceptor(GrpcTracing grpcTracing) {
//        return grpcTracing.newServerInterceptor();
//    }
//}
