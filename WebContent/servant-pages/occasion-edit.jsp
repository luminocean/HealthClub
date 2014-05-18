<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<div class="form-group">
	<label class="col-md-1 control-label">教练</label>

	<div class="btn-group col-md-1">
		<select class="occasion-attr" name="coaches">
			<s:iterator var="item" value="#request['coaches']">
					<s:if test="name==#occasion.coach.name">
						<option value='<s:property value="name"/>' selected="selected">
							<s:property value="name" />
						</option>
					</s:if>
					
					<s:else>
						<option value='<s:property value="name"/>'>
							<s:property value="name" />
						</option>
					</s:else>
			</s:iterator>
		</select>
	</div>


	<label class="col-md-1 control-label">场地</label>

	<div class="btn-group col-md-1">
		
		<select class="occasion-attr" name="places">
			<s:iterator var="item" value="#request['places']">

					<s:if test="place==#occasion.place.place">
						<option value='<s:property value="place"/>' selected="selected">
							<s:property value="place" />
						</option>
					</s:if>
					
					<s:else>
						<option value='<s:property value="place"/>'>
							<s:property value="place" />
						</option>
					</s:else>

			</s:iterator>
		</select>
	</div>


	<label class="col-md-1 control-label">时间</label>

	<div class="col-md-2">
		<s:if test="time">
			<input class="datetimepicker occasion-attr" name="times" type="text" 
				value="<s:property value="stringTime"/>">
		</s:if>
		
		<s:else>
			<input class="datetimepicker occasion-attr" name="times" type="text">
		</s:else>
	</div>

		
</div>
