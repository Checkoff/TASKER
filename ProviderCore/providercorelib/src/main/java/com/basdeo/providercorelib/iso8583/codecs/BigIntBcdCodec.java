/*
 * j8583 A Java implementation of the ISO8583 protocol
 * Copyright (C) 2007 Enrique Zamudio Lopez
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */
package com.basdeo.providercorelib.iso8583.codecs;

import com.basdeo.providercorelib.iso8583.CustomBinaryField;
import com.basdeo.providercorelib.iso8583.util.Bcd;

import java.math.BigInteger;

/**
 * A custom field encoder/decoder to be used with LLBIN/LLLBIN fields
 * that contain BigIntegers in BCD encoding.
 *
 * @author Enrique Zamudio
 *         Date: 07/05/13 13:02
 */
public class BigIntBcdCodec implements CustomBinaryField<BigInteger> {

    @Override
    public BigInteger decodeBinaryField(byte[] value, int pos, int len) {
        return Bcd.decodeToBigInteger(value, pos, len*2);
    }

    @Override
    public byte[] encodeBinaryField(BigInteger value) {
        final String s = value.toString(10);
        final byte[] buf = new byte[s.length() / 2 + s.length() % 2];
        Bcd.encode(s, buf);
        return buf;
    }

    @Override
    public BigInteger decodeField(String value) {
        return new BigInteger(value, 10);
    }

    @Override
    public String encodeField(BigInteger value) {
        return value.toString(10);
    }

}