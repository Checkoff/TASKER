/*
j8583 A Java implementation of the ISO8583 protocol
Copyright (C) 2011 Enrique Zamudio Lopez

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
*/
package com.basdeo.providercorelib.iso8583.parse;


import com.basdeo.providercorelib.iso8583.CustomField;
import com.basdeo.providercorelib.iso8583.IsoType;
import com.basdeo.providercorelib.iso8583.IsoValue;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/** This class is used to parse fields of type LLVAR.
 * 
 * @author Enrique Zamudio
 */
public class LlvarParseInfo extends FieldParseInfo {

	public LlvarParseInfo() {
		super(IsoType.LLVAR, 0);
	}

    @Override
	public <T> IsoValue<?> parse(final int field, final byte[] buf,
								 final int pos, final CustomField<T> custom)
			throws ParseException, UnsupportedEncodingException {
		if (pos < 0) {
			throw new ParseException(String.format("Invalid LLVAR field %d %d", field, pos), pos);
		} else if (pos+2 > buf.length) {
			throw new ParseException(String.format("Insufficient data for LLVAR header, pos %d", pos), pos);
		}
		length = decodeLength(buf, pos, 2);
		if (length < 0) {
			throw new ParseException(String.format(
                    "Invalid LLVAR length %d, field %d pos %d", length, field, pos), pos);
		} else if (length+pos+2 > buf.length) {
			throw new ParseException(String.format(
                    "Insufficient data for LLVAR field %d, pos %d", field, pos), pos);
		}
		String _v;
        try {
            _v = length == 0 ? "" : new String(buf, pos + 2, length, getCharacterEncoding());
        } catch (IndexOutOfBoundsException ex) {
            throw new ParseException(String.format(
                    "Insufficient data for LLVAR header, field %d pos %d", field, pos), pos);
        }
		//This is new: if the String's length is different from the specified length in the buffer,
		//there are probably some extended characters. So we create a String from the rest of the buffer,
		//and then cut it to the specified length.
		if (_v.length() != length) {
			_v = new String(buf, pos + 2, buf.length-pos-2, getCharacterEncoding()).substring(0, length);
		}
		if (custom == null) {
			return new IsoValue<String>(type, _v, length, null);
		} else {
            T dec = custom.decodeField(_v);
            return dec == null ? new IsoValue<String>(type, _v, length, null) :
                    new IsoValue<T>(type, dec, length, custom);
		}
	}

    @Override
	public <T> IsoValue<?> parseBinary(final int field, final byte[] buf,
                                   final int pos, final CustomField<T> custom)
			throws ParseException, UnsupportedEncodingException {
		if (pos < 0) {
			throw new ParseException(String.format("Invalid bin LLVAR field %d pos %d",
                    field, pos), pos);
		} else if (pos+1 > buf.length) {
			throw new ParseException(String.format(
                    "Insufficient data for bin LLVAR header, field %d pos %d", field, pos), pos);
		}
		length = (((buf[pos] & 0xf0) >> 4) * 10) + (buf[pos] & 0x0f);
		if (length < 0) {
			throw new ParseException(String.format(
                    "Invalid bin LLVAR length %d, field %d pos %d", length, field, pos), pos);
		}
		if (length+pos+1 > buf.length) {
			throw new ParseException(String.format(
                    "Insufficient data for bin LLVAR field %d, pos %d", field, pos), pos);
		}
		if (custom == null) {
			return new IsoValue<String>(type, new String(buf, pos + 1, length, getCharacterEncoding()), null);
		} else {
            T dec = custom.decodeField(new String(buf, pos + 1, length, getCharacterEncoding()));
            return dec == null ? new IsoValue<String>(type, new String(buf, pos + 1, length, getCharacterEncoding()), null) :
                    new IsoValue<T>(type, dec, custom);
		}
	}

}
