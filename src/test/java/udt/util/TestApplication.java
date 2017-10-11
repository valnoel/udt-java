package udt.util;

import org.junit.Assert;
import org.junit.Test;

public class TestApplication {

	@Test
	public void encodeDecode() {
		final int value = 123456;
		final byte[] encoded = Application.encode(value);
		final int decoded = Application.decode(encoded);
		Assert.assertEquals(value, decoded);
	}

	@Test
	public void encode0() {
		final int value = 0;
		final byte[] encoded = Application.encode(value);
		Assert.assertArrayEquals(new byte[]{0,0,0,0}, encoded);
	}

	@Test
	public void decode0() {
		final byte[] data = new byte[]{0,0,0,0};
		final int decoded = Application.decode(data);
		Assert.assertEquals(0, decoded);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decodeIllegal3Bytes() {
		final byte[] data = new byte[]{0,0,0};
		Application.decode(data);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decodeIllegal8Bytes() {
		final byte[] data = new byte[]{0,0,0,0,0,0,0,0};
		Application.decode(data);
	}

	@Test
	public void encodeDecode64() {
		final long value = 46629161696L;
		final byte[] encoded = Application.encode64(value);
		final long decoded = Application.decode64(encoded);
		Assert.assertEquals(value, decoded);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decode64Illegal4Bytes() {
		final byte[] data = new byte[]{0,0,0};
		Application.decode(data);
	}

	@Test(expected = IllegalArgumentException.class)
	public void decode64Illegal9Bytes() {
		final byte[] data = new byte[]{0,0,0,0,0,0,0,0,0};
		Application.decode(data);
	}

}
