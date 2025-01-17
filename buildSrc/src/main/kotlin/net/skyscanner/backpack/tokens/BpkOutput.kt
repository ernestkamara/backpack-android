/**
 * Backpack for Android - Skyscanner's Design System
 *
 * Copyright 2018 Skyscanner Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.skyscanner.backpack.tokens

import com.google.common.io.Resources
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import java.nio.charset.StandardCharsets

sealed class BpkOutput<Input> : (Input) -> Boolean {

  data class KotlinFile(
    val srcDir: String,
    val pkg: String,
  ) : BpkOutput<TypeSpec>() {

    override fun invoke(typeSpec: TypeSpec): Boolean {
      FileSpec.builder(pkg, typeSpec.name!!)
        .addFileHeader()
        .suppressWarningTypes("RedundantVisibilityModifier", "unused")
        .addType(typeSpec)
        .build()
        .writeTo(File(srcDir))
      return true
    }
  }

  data class KotlinExtensionFiles(
    val srcDir: String,
    val pkg: String,
  ) : BpkOutput<List<PropertySpec>>() {

    override fun invoke(properties: List<PropertySpec>): Boolean {

      properties.forEach { property ->
        FileSpec.builder(pkg, property.name)
          .addFileHeader()
          .suppressWarningTypes("RedundantVisibilityModifier", "unused")
          .addProperty(property)
          .build()
          .writeTo(File(srcDir))
      }

      return true
    }

  }

}

private fun FileSpec.Builder.addFileHeader() : FileSpec.Builder {
  val copyright = Resources.toString(Resources.getResource("copyright.txt"), StandardCharsets.UTF_8)
    return this
      .addFileComment(copyright)
    .addFileComment("Auto-generated: do not edit")
}

private fun FileSpec.Builder.suppressWarningTypes(vararg types: String) : FileSpec.Builder {
  if (types.isEmpty()) {
    return this
  }

  val format = "%S,".repeat(types.count()).trimEnd(',')
  return addAnnotation(
    AnnotationSpec.builder(ClassName("", "Suppress"))
      .addMember(format, *types)
      .build()
  )
}
