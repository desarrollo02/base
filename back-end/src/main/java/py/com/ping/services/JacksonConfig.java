package py.com.ping.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class JacksonConfig extends ResteasyJackson2Provider {

    private Logger log;
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String,Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        log = Logger.getLogger(JacksonConfig.class.getName());
        //get the Object Mapper
        ObjectMapper mapper = locateMapper(type, mediaType);
        // Set human readable date format
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        mapper.getSerializationConfig().with(sdf);
        //send null values
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.ALWAYS);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        super.writeTo(value, type, genericType, annotations, mediaType, httpHeaders, entityStream);
    }
}
