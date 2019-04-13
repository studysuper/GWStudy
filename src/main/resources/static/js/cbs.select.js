/**
 * 基础组件定义
 */
+function($, window, undefined) {

	'use strict';

	window.cbs = window.cbs || {};

	// 给select添加option
	function setOptions(select, options) {
		var ele = $(select), nulltext, first, value, opt, appendValue;
		value = ele.attr('value') || ele.attr('default-value');
		nulltext = ele.attr("nulltext");
		appendValue = ele.attr("append-value");
		if (!!options) {
			options.sort(function(o1, o2) {
				return o1.value - o2.value;
			});
			first = ele.find("option:first");
			if (!nulltext && !!first && !first.val()) {
				nulltext = first.text();
			}
			ele.empty();
			if (!!nulltext) {
				ele.append('<option value="">' + nulltext + '</option>');
			}
			$.each(options, function(index, item) {
				opt = '<option value="' + item.value + '" ' + (item.value == value ? 'selected = "selected"' : '')
						+ '>' + (!!appendValue ? item.value + '-' + item.text : item.text) + '</option>';
				ele.append(opt);
			});
		}
	}

	function objectToOptionValues(obj) {
		var options = [];
		if (!!obj) {
			$.each(obj, function(index, item) {
				options.push({
					value : index,
					text : item
				});
			});
		}
		return options;
	}

	// 将获取到的地区转换为options
	function setAreaOptions(element, parentCode) {
		var areas, options = [];
		if (!!parentCode) {
			areas = cbs.areas.get(parentCode);
			if (!!areas) {
				options = objectToOptionValues(areas);
			}
		}
		setOptions(element, options);
	}

	// 设置值为null
	function setNull(elements) {
		if (cbs.isArray(elements)) {
			$.each(elements, function(index, item) {
				$(item).val('');
				setOptions(item, []);
			});
		} else if (cbs.isString(elements)) {
			$(elements).val('');
			setOptions(elements, []);
		}
	}

	// 筛选产品信息
	function riskToOption(element, fullcodes, config) {
		var valueField = config.valueField || 'riskCode', textField = config.textField || 'riskShortName', codes = {};
		cbs.arraySelect(element, fullcodes, valueField, textField);
	}
	// 清空select标签选项内容
	cbs.cleanSelect = function(elements) {
		setNull(elements);
	};

	// 对象数组转换为select 的options 选项
	cbs.arraySelect = function(elements, arr, valueField, textField) {
		var options = [];
		if (cbs.isArray(elements)) {
			$.each(elements, function(i, e) {
				cbs.arraySelect(e, arr, valueField, textField);
			});
		} else {
			if (!!arr) {
				// 单对象转换为数组
				if (!cbs.isArray(arr)) {
					arr = [ arr ];
				}
				$.each(arr, function(index, item) {
					options.push({
						value : item[valueField],
						text : item[textField]
					});
				});
			}
			setOptions(elements, options);
		}
	};

	// 动态添加select 的 options选项
	cbs.ajaxSelect = function(elements, url, valueField, textField, dataField) {
		var vdata, options = [];
		cbs.httpclient.get(url, function(data) {
			if (!!data) {
				if (!!dataField) {
					vdata = data[dataField];
				} else {
					vdata = data;
				}
				$.each(vdata, function(index, item) {
					options.push({
						value : item[valueField],
						text : item[textField]
					});
				});
				if (cbs.isString(elements)) {
					setOptions(elements, options);
				} else if (cbs.isArray(elements)) {
					$.each(elements, function(index, item) {
						setOptions(item, options);
					});
				}
			}
		});
	};

	// 数据字典转换select的options
	cbs.codeSelect = function(elements, code, parentValue) {
		var codeValues = cbs.codes.get(code), length = elements.length, value;
		if (!!parentValue) {
			codeValues = cbs.codes.get(code, parentValue);
		}
		if (!!codeValues) {
			if (cbs.isString(elements)) {
				setOptions(elements, objectToOptionValues(codeValues));
			} else if (cbs.isArray(elements)) {
				setOptions(elements[0], objectToOptionValues(codeValues));
				if (length > 1) {
					value = $(elements[0]).val();
					if (value == '' || value == undefined) {
						setNull(elements.slice(1));
					} else {
						cbs.codeSelect(elements.slice(1), code, value);
					}
				}
				$.each(elements, function(index, item) {
					if (index + 1 < length) {
						$(item).bind('change', function() {
							var val = $(this).val();
							if (!val) {
								setNull(elements.slice(index + 1));
							} else {
								cbs.codeSelect(elements[index + 1], code, val);
								if (length > index + 2) {
									setNull(elements.slice(index + 2));
								}
							}
						});
					}
				});
			} else if (cbs.isObject(elements)) { // 同类型标签批量处理
				$.each(elements, function(index, item) {
					setOptions(item, objectToOptionValues(codeValues));
				});
			}
		}
	};

	// 数据字典转换为checkbox 或者 radio
	cbs.codeCheck = function(element, code, type, parentValue) {
		var codeValues = cbs.codes.get(code), value, name, html = '';
		if (!!parentValue) {
			codeValues = cbs.codes.get(code, parentValue);
		}
		if (!!codeValues) {
			element = $(element);
			value = element.val();
			if(!!value) {
				value += ',';
			}
			name = element.attr('name');
			element.attr('name', name + "_old");
			$.each(codeValues, function(val, text) {
				if(type == 'radio') {
					html += '<span class="checkr"><label>';
					html += '<input type="radio" value="' + val + '" name = "' + name + '"';
					if(value.indexOf(val + ',') >= 0) {
						html += ' checked';
					}
					html += '><b></b>' + text + '</label></span>'	
				} else {
					html += '<span class="checkb"><label>';
					html += '<input type="checkbox" value="' + val + '" name = "' + name + '"';
					if(value.indexOf(val + ',') >= 0) {
						html += ' checked';
					}
					html += '><b></b>' + text + '</label></span>'
				}
			});
			element.after(html);
		}
	};

	// 省市县三级联动
	cbs.areaSelect = function(province, city, county) {
		var pele = $(province), cele = $(city), oele = $(county), pvalue = pele.attr('value'), cvalue = cele
				.attr('value');
		if (!!pele && !pele.attr('nulltext')) {
			pele.attr('nulltext', '请选择省');
		}
		if (!!cele && !cele.attr('nulltext')) {
			cele.attr('nulltext', '请选择市');
		}
		if (!!oele && !oele.attr('nulltext')) {
			oele.attr('nulltext', '请选择区县');
		}
		setAreaOptions(province, 'root');
		setAreaOptions(city, pvalue);
		setAreaOptions(county, cvalue);
		$(province).on('change', function() {
			setAreaOptions(city, $(this).val());
			setAreaOptions(county);
		});
		$(city).on('change', function() {
			setAreaOptions(county, $(this).val());
		});
	};

	// 供应商字典
	cbs.supplierSelect = function(element, parentCode) {
		var config = {
			valueField : 'supplierCode',
			textField : 'supplierShortName'
		}, suppliers;
		if (cbs.isString(parentCode)) {
			config.parentCode = parentCode;
		} else if (cbs.isObject(parentCode)) {
			config = $.extend(config, parentCode);
		}
		suppliers = cbs.suppliers.get(config);
		if (!!suppliers) {
			cbs.arraySelect(element, suppliers, config.valueField, config.textField);
		}
	};

	// 产品字典
	cbs.riskSelect = function(element, supplierCode, config) {
		var codes = {}, fullcodes, key;
		if (!!supplierCode) {
			key = 'risk-' + supplierCode;
			if (cbs.isObject(config)) {
				if (!!config.saasId) {
					key += config.saasId;
				}
			} else {
				config = {};
			}
			fullcodes = cbs.storage.get(key, cbs.storage.SESSION);
			if (!fullcodes) {
				cbs.httpclient.get(_ctx + '/product/cpRisk/code/' + supplierCode + "/"
						+ (!!config.saasId ? config.saasId : "base"), function(data) {
					if (!!data && data.code == 0 && data.codes.length > 0) {
						codes = {};
						cbs.storage.set(key, data.codes, cbs.storage.SESSION);
						setOptions(element, riskToOption(element, data.codes, config));
					} else {
						setOptions(element, objectToOptionValues(codes));
					}
				});
			} else {
				setOptions(element, riskToOption(element, fullcodes, config));
			}
		} else {
			setOptions(element, objectToOptionValues(codes));
		}

	};

	$.fn.jstreeSelectedValues = function(flag) {
		var values = $(this).jstree().get_checked();
		if (flag) {
			$(this).find(".jstree-undetermined").each(function(i, element) {
				values.push($(element).closest('.jstree-node').attr("id"));
			});
		}
		return values;
	};

	cbs.glyphiconSearch = function(element) {
		var ele = $(element), vlu, reg, arrvl, pickval;
		ele.prev().find(".bs-searchbox").css('position', 'relative').append(
				"<span class='selectsearch'><span class=\"glyphicon glyphicon-search\"></span></span>");
		ele.prev().find(".selectsearch").on('click', function(e) {
			window.event ? window.event.cancelBubble = true : e.stopPropagation();
			vlu = ele.prev().find(".bs-searchbox .form-control").val().replace(/\s+/g, "").replace(/，/g, ",");
			reg = /^[0-9a-zA-Z,]+$/.test(vlu);
			if (cbs.isString(vlu) && reg) {
				arrvl = vlu.split(',');
				ele.selectpicker('val', (arrvl));
				pickval = ele.selectpicker('val');
				if (!pickval)
					pickval = [];
				ele.parent().find(".dropdown-menu .selected").removeClass('hidden');
				if (!!pickval && !!arrvl && pickval.length < arrvl.length) {
					layer.msg(getArrDifference(pickval, arrvl) + '没有对应内容');
				}
				;
			} else {
				layer.msg('请输入号码并将号码之前用逗号分隔开');
			}
			;
		});
	};
	cbs.removeGlyphiconSearch = function(element) {
		var ele = $(element);
		ele.prev().find(".selectsearch").remove();
	};
	function getArrDifference(arr1, arr2) {
		return arr1.concat(arr2).filter(function(v, i, arr) {
			return arr.indexOf(v) === arr.lastIndexOf(v);
		});
	}
}(jQuery, window);
