package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

class PipelinePort(
    destination: UUID,
) {
   public val id: UUID = Id.genId() 
}
