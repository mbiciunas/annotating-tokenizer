/*
 * Copyright 2013 Mark Biciunas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agorex.parse.token;

import java.util.Locale;




/**
 * @author mbiciunas
 *
 */
public final class SourceData {

   private static final int CASE_CONVERSION = 32;

   private transient String source;
   private transient int sourceLength;


   /**
    * @param source string containing the data to be parsed.
    */
   public void initialize(final String source) {
      this.source = source;
      sourceLength = this.source.length();
   }


   /**
    * @return length of the source string.
    */
   public int getSourceLength() { return sourceLength; }


   /**
    * @param pointer position of the character we are looking for.
    * @return character at the position in the source string.
    */
   public char getCharacter(final int pointer) {
      rangeCheck(pointer);

      return source.charAt(pointer);
   }


   /**
    * @param pointerStart start position of the source section.
    * @param pointerEnd end position of the source section.
    * @return string containing the source between the start and end pointers.
    */
   public String getSource(final int pointerStart, final int pointerEnd) {
      rangeCheck(pointerStart);
      rangeCheck(pointerEnd);

      return source.substring(pointerStart, pointerEnd);
   }


   /**
    * @param tokenData object containing all the tokens for the source.
    * @param index position of token within the token data array.
    * @return string containing the data identified by the token.
    */
   public String getToken(final TokenData tokenData, final int index) {
      return source.substring(tokenData.getStartPointer(index), tokenData.getEndPointer(index));
   }


   /**
    * @param tokenData object containing all the tokens for the source.
    * @param index position of token within the token data array.
    * @return lower case string containing the data identified by the token.
    */
   public String getTokenLowerCase(final TokenData tokenData, final int index) {
      return source.substring(tokenData.getStartPointer(index), tokenData.getEndPointer(index)).toLowerCase(Locale.getDefault());
   }


   /**
    * @param tokenData object containing all the tokens for the source.
    * @param index position of token within the token data array.
    * @param valueLowerCase value we want to compare to the source.
    * @return true if the value matches the source data identified by the token.
    */
   public boolean compareLowerCase(final TokenData tokenData, final int index, final String valueLowerCase) {
      boolean same = true;
      final int length;
      final int pointerStart;

      if (valueLowerCase == null) {
         throw new IllegalArgumentException("arrayLowerCase is null");
      }

      length = valueLowerCase.length();
      pointerStart = tokenData.getStartPointer(index);

      if (length + pointerStart != tokenData.getEndPointer(index)) {
         same = false;
      }
      else {
         for (int i = 0; i < length; i++) {
            if (valueLowerCase.charAt(i) != source.charAt(pointerStart + i) && valueLowerCase.charAt(i) - CASE_CONVERSION != source.charAt(pointerStart + i)) {
               same = false;
            }
         }
      }

      return same;
   }


   private void rangeCheck(final int pointer) {
      if (pointer < 0 || pointer > sourceLength) {
         throw new IllegalArgumentException("pointer value " + pointer + " must be between 0 and the source length " + sourceLength);
      }
   }
}
