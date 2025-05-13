export const AppConfig = {
    springApiUrl: getSpringApiUrl()
  };
  
  function getSpringApiUrl(): string {
    if (window.location.hostname === 'localhost') {
      // Development environment
      return 'http://localhost:8080';
    } else {
      // Production environment
      return 'http://spring-money-app-env.eba-trinnfcp.us-east-2.elasticbeanstalk.com';
    }
  }
  