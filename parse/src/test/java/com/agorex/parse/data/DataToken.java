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
package com.agorex.parse.data;

import com.agorex.parse.token.TokenData;


public final class DataToken {

   private final transient TokenData tokenData = new TokenData(100);


   public void add(final int pointerStart, final int pointerEnd, final int annotation) {
      tokenData.add(pointerStart, pointerEnd, annotation);
   }


   public int getSize() {
      return tokenData.getSize();
   }


   public int getPointerStart(final int index) {
      return tokenData.getStartPointer(index);
   }


   public int getPointerEnd(final int index) {
      return tokenData.getEndPointer(index);
   }


   public int getAnnotation(final int index) {
      return tokenData.getAnnotation(index);
   }
}