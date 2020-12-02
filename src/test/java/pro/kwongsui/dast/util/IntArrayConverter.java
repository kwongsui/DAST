package pro.kwongsui.dast.util;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class IntArrayConverter implements ArgumentConverter {

  @Override
  public Object convert(Object source, ParameterContext context)
      throws ArgumentConversionException {
    if (!(source instanceof String)) {
      throw new IllegalArgumentException(
          "The argument should be a string: " + source);
    }
    try {
      if (((String) source).length() == 0) {
        return new Integer[0];
      }
      String[] ints = ((String) source).split(",");
      int[] ret = new int[ints.length];
      int idx = 0;
      for (String o : ints) {
        ret[idx++] = Integer.parseInt(o);
      }
      return ret;
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert", e);
    }
  }
}
