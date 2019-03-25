/**
 * @author ZQ
 * @date 2019/3/25 19:52
 * @comment 校验框架修改，基于jquery validator框架，将校验配置转移到html表单元素中。
 * @msg1 在表单元素添加v-开头的属性，框架自动解析为校验项目；属性值为value-message的模式。 如校验一个字段的最大长度，可添加属性
 *       v-maxlength='100-字段最大长度不可超过100'。
 * @support 框架可支持jquery validator所有校验类型，也可以支持自定义类型。
 *          目前支持的校验类型包括：required，remote，email，url，date，dateISO，number，digits，creditcard，equalTo，accept，
 *          maxlength:，minlength，rangelength，range，max，min， ip
 */
+function($, window, undefined) {

	'use strict';

	window.cbs = window.cbs || {};

	var icon = "<i class='fa fa-times-circle'></i> ", baseConfig = {
		rules : {},
		messages : {}
	};
	var h5Attrs = ',required,min,max,pattern,maxlength', formatRules = ',min,max,pattern,maxlength,minlength';

	$.validator.addMethod('ip', function(value, element, params) {
		var reg = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
		if (reg.exec(value) != null) {
			if (RegExp.$1 < 0 || RegExp.$1 > 255) {
				return false;
			}
			if (RegExp.$2 < 0 || RegExp.$2 > 255) {
				return false;
			}
			if (RegExp.$3 < 0 || RegExp.$3 > 255) {
				return false;
			}
			if (RegExp.$4 < 0 || RegExp.$4 > 255) {
				return false;
			}
		} else {
			return false
		}
		return true;
	});

	function addMessage(config, field, rule, message) {
		if (formatRules.indexOf(rule) > 0 && !!message) {
			config.messages[field][rule] = $.validator.format(message);
		} else {
			config.messages[field][rule] = message;
		}
	}

	/**
	 * 添加校验信息，
	 */
	function addValidateRule(config, element, field, rule, message) {
		if (!field || !rule) {
			return '';
		}
		if (typeof $.validator.methods[rule] != 'function') {
			console.error('validate method ' + rule + ' not found');
			return;
		}
		var msgArr;
		config = config || $.extend({}, baseConfig);
		config.rules[field] = config.rules[field] || {};
		config.messages[field] = config.messages[field] || {};
		if (!!config.rules[field][rule]) {
			console.log('field rule ' + field + ':' + rule + 'exists!')
			return;
		}
		msgArr = !message ? [] : message.split('-');
		switch (msgArr.length) {
		case 0:
			config.rules[field][rule] = true;
			config.messages[field][rule] = '请输入' + rule;
			break;
		case 1:
			config.rules[field][rule] = true;
			addMessage(config, field, rule, msgArr[0]);
			addElementAttr(element, rule);
			break;
		case 2:
			if (rule.startsWith('range')) {
				config.rules[field][rule] = msgArr[0].split(',');
			} else {
				config.rules[field][rule] = msgArr[0];
			}
			addMessage(config, field, rule, msgArr[1]);
			addElementAttr(element, rule, msgArr[0]);
			break;
		default:
			config.rules[field][rule] = true;
		}
		return config;
	}

	/**
	 * 将H5支持的属性添加到元素
	 */
	function addElementAttr(element, attrName, attrValue) {
		if (!element || !attrName) {
			return;
		}
		if (h5Attrs.indexOf(attrName.toLowerCase()) > 0) {
			element.attr(attrName, !!attrValue ? attrValue : attrName);
		}
	}

	cbs.validate = function(form, config) {
		var _config = config || $.extend({}, baseConfig), _this, _field, _rule, _message;

		if (!form || !$(form)) {
			layer.alert('表单' + form + '未找到');
			return;
		}
		var arr = $(form).find('input,textarea');
		$(form).find('input,textarea,select').each(function(index, item) {
			_this = $(item);
			_field = _this.attr('name');
			$.each(item.attributes, function() {
				if (this.specified && this.name.startsWith('v-')) {
					_rule = this.name.substring(2);
					_message = this.value;
					addValidateRule(_config, _this, _field, _rule, _message);
				}
			});
			if (!!_this.attr('v-required')) {
				var star = _this.next('span');
				if (!star || !star.hasClass('star')) {
					_this.after('<span class="star">*</span>');
				}
			}
		});
		$(form).validate(_config);
	};

}(jQuery, window);
